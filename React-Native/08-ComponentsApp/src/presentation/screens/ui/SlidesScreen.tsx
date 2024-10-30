import {Image, ImageSourcePropType, useWindowDimensions} from 'react-native';
import {Text, View} from 'react-native';
import {colors, globalStyles} from '../../../config/theme/theme';
import {FlatList} from 'react-native-gesture-handler';

interface Slide {
  title: string;
  desc: string;
  img: ImageSourcePropType;
}

const items: Slide[] = [
  {
    title: 'Titulo 1',
    desc: 'Ea et eu enim fugiat sunt reprehenderit sunt aute quis tempor ipsum cupidatat et.',
    img: require('../../assets/slide-1.png'),
  },
  {
    title: 'Titulo 2',
    desc: 'Anim est quis elit proident magna quis cupidatat curlpa labore Lorem ea. Exercitation mollit velit in aliquip tempor occaecat dolor minim amet dolor enim cillum excepteur. ',
    img: require('../../assets/slide-2.png'),
  },
  {
    title: 'Titulo 3',
    desc: 'Ex amet duis amet nulla. Aliquip ea Lorem ea culpa consequat proident. Nulla tempor esse ad tempor sit amet Lorem. Velit ea labore aute pariatur commodo duis veniam enim.',
    img: require('../../assets/slide-3.png'),
  },
];

export const SlidesScreen = () => {
  return (
    <View
      style={{
        flex: 1,
        backgroundColor: colors.background,
      }}>
      <FlatList
        data={items}
        keyExtractor={item => item.title}
        renderItem={({item}) => <SlideItem item={item} />}
        horizontal
        pagingEnabled
        decelerationRate={'fast'}
        // scrollEnabled={false}
      />
    </View>
  );
};

interface SlideItemProps {
  item: Slide;
}

const SlideItem = ({item}: SlideItemProps) => {
  const {width} = useWindowDimensions();
  const {title, img, desc} = item;

  return (
    <View
      style={{
        flex: 1,
        backgroundColor: 'white',
        borderRadius: 5,
        padding: 40,
        justifyContent: 'center',
        width: width,
      }}>
      <Image
        source={img}
        style={{
          width: width * 0.7,
          height: width * 0.7,
          resizeMode: 'center',
          alignSelf: 'center',
        }}
      />

      <Text
        style={[
          globalStyles.title,
          {
            color: colors.primary,
          },
        ]}>
        {title}
      </Text>

      <Text
        style={{
          color: colors.text,
          marginTop: 20,
        }}>
        {desc}
      </Text>
    </View>
  );
};
