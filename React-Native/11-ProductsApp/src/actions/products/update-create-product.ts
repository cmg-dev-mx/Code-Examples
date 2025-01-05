import {isAxiosError} from 'axios';
import {tesloApi} from '../../config/api/tesloApi';
import {Product} from '../../domain/entities/product';

export const updateCreateProduct = (product: Partial<Product>) => {
  product.stock = isNaN(Number(product.stock)) ? 0 : Number(product.stock);
  product.price = isNaN(Number(product.price)) ? 0 : Number(product.price);

  if (product.id && product.id !== 'new') {
    return updateProduct(product);
  }

  return createProduct(product);
};

// TODO: Revisar si viene el usuario
const updateProduct = async (product: Partial<Product>) => {
  const {id, images = [], ...rest} = product;

  try {
    const checkedImages = await prepareImages(images);
    const {data} = await tesloApi.patch(`/products/${id}`, {
      images: checkedImages,
      ...rest,
    });
    return data;
  } catch (error) {
    if (isAxiosError(error)) {
      console.log(error.response?.data);
    }

    return createProduct(product);
  }
};

const prepareImages = async (images: string[]) => {
  const fileImages = images.filter(image => image.startsWith('file://'));
  const currentImages = images.filter(image => !image.startsWith('file://'));

  if (fileImages.length > 0) {
    const uploadedPromises = fileImages.map(uploadImages);
    const uploadedImages = await Promise.all(uploadedPromises);

    currentImages.push(...uploadedImages);
  }

  return currentImages.map(image => image.split('/').pop());
};

const uploadImages = async (image: string) => {
  const formData = new FormData();
  formData.append('file', {
    uri: image,
    name: image.split('/').pop(),
    type: 'image/jpeg',
  });

  const {data} = await tesloApi.post<{image: string}>(
    '/files/product',
    formData,
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    },
  );

  return data.image;
};

const createProduct = async (product: Partial<Product>) => {
  const {id, images = [], ...rest} = product;

  try {
    const checkedImages = await prepareImages(images);
    const {data} = await tesloApi.post(`/products/`, {
      images: checkedImages,
      ...rest,
    });
    return data;
  } catch (error) {
    if (isAxiosError(error)) {
      console.log(error.response?.data);
    }

    throw new Error('Error al crear el producto');
  }
};
