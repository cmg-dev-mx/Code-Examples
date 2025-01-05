import {useRef} from 'react';
import {
  Button,
  ButtonGroup,
  Input,
  Layout,
  useTheme,
} from '@ui-kitten/components';
import {Formik} from 'formik';

import {MainLayout} from '../../layouts/MainLayout';
import {useMutation, useQuery, useQueryClient} from '@tanstack/react-query';
import {StackScreenProps} from '@react-navigation/stack';
import {RootStackParams} from '../../navigation/StackNavigator';

import {ScrollView} from 'react-native-gesture-handler';
import {Product} from '../../../domain/entities/product';
import {MyIcon} from '../../components/ui/MyIcon';

import {ProductImages} from '../../components/products/ProductImages';
import {updateCreateProduct, getProductById} from '../../../actions/products';
import {genders, sizes} from '../../../config/constants/constants';
import {CameraAdapter} from '../../../config/adapters/camera-adapter';

interface Props extends StackScreenProps<RootStackParams, 'ProductScreen'> {}

export const ProductScreen = ({route}: Props) => {
  const productIdRef = useRef(route.params.productId);
  const theme = useTheme();
  const queryClient = useQueryClient();

  const {data: product} = useQuery({
    queryKey: ['product', productIdRef.current],
    queryFn: () => getProductById(productIdRef.current),
  });

  const mutation = useMutation({
    mutationFn: (data: Product) =>
      updateCreateProduct({...data, id: productIdRef.current}),
    onSuccess(data: Product) {
      productIdRef.current = data.id;
      queryClient.invalidateQueries({queryKey: ['products', 'infinite']});
      queryClient.invalidateQueries({queryKey: ['product', data.id]});
    },
  });

  if (!product) {
    return <MainLayout title="Cargando..." />;
  }

  return (
    <Formik initialValues={product} onSubmit={mutation.mutate}>
      {({handleChange, handleSubmit, values, errors, setFieldValue}) => (
        <MainLayout
          title={values.title}
          subTitle={`Precio: $${values.price}`}
          rightAction={async () => {
            const photos = await CameraAdapter.takePicture();
            setFieldValue('images', [...values.images, ...photos]);
          }}
          rightActionIcon="camera-outline">
          <ScrollView style={{flex: 1}}>
            {/* Imágenes del producto */}
            <Layout
              style={{
                marginVertical: 10,
                justifyContent: 'center',
                alignItems: 'center',
              }}>
              <ProductImages images={values.images} />
            </Layout>

            {/* Formulario */}
            <Layout style={{marginHorizontal: 10}}>
              <Input
                style={{marginVertical: 5}}
                label="Título"
                value={values.title}
                onChangeText={handleChange('title')}
              />
              <Input
                style={{marginVertical: 5}}
                label="Slug"
                value={values.slug}
                onChangeText={handleChange('slug')}
              />
              <Input
                style={{marginVertical: 5}}
                multiline
                numberOfLines={5}
                label="Descripción"
                value={values.description}
                onChangeText={handleChange('description')}
              />
            </Layout>

            <Layout
              style={{
                marginHorizontal: 15,
                marginVertical: 5,
                flexDirection: 'row',
                gap: 10,
              }}>
              <Input
                style={{flex: 1}}
                keyboardType="numeric"
                label="Precio"
                value={values.price.toString()}
                onChangeText={handleChange('price')}
              />
              <Input
                style={{flex: 1}}
                keyboardType="numeric"
                label="Inventario"
                value={values.stock.toString()}
                onChangeText={handleChange('stock')}
              />
            </Layout>

            {/* Selectores */}
            <ButtonGroup
              style={{
                margin: 2,
                marginTop: 30,
                marginHorizontal: 15,
              }}
              size="small"
              appearance="outline">
              {sizes.map(size => (
                <Button
                  style={{
                    flex: 1,
                    backgroundColor: values.sizes.includes(size)
                      ? theme['color-primary-200']
                      : undefined,
                  }}
                  key={size}
                  onPress={() =>
                    setFieldValue(
                      'sizes',
                      values.sizes.includes(size)
                        ? values.sizes.filter(s => s !== size)
                        : [...values.sizes, size],
                    )
                  }>
                  {size}
                </Button>
              ))}
            </ButtonGroup>
            <ButtonGroup
              style={{
                margin: 2,
                marginTop: 30,
                marginHorizontal: 15,
              }}
              size="small"
              appearance="outline">
              {genders.map(gender => (
                <Button
                  style={{
                    flex: 1,
                    backgroundColor: values.gender.startsWith(gender)
                      ? theme['color-primary-200']
                      : undefined,
                  }}
                  key={gender}
                  onPress={() => setFieldValue('gender', gender)}>
                  {gender}
                </Button>
              ))}
            </ButtonGroup>

            {/* Botón de guardar */}
            <Button
              accessoryLeft={<MyIcon name="save-outline" white />}
              style={{margin: 15}}
              onPress={() => handleSubmit()}
              disabled={mutation.isPending}
              size="large">
              Guardar
            </Button>

            <Layout style={{height: 200}} />
          </ScrollView>
        </MainLayout>
      )}
    </Formik>
  );
};
