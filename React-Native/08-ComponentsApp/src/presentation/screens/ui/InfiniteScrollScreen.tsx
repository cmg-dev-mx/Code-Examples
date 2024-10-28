import {useState} from 'react';
import {Text, View} from 'react-native';
import {CustomView} from '../../components/ui/CustomView';
import {Title} from '../../components/ui/Title';
import {FlatList} from 'react-native-gesture-handler';
import {colors} from '../../../config/theme/theme';

export const InfiniteScrollScreen = () => {
  const [numbers, setNumbers] = useState([0, 1, 2, 3, 4, 5, 6, 7, 8, 9]);

  const loadMore = () => {
    console.log('Cargando más...');
    const newArray = Array.from({length: 10}, (_, i) => i + numbers.length);

    setTimeout(() => {
      setNumbers([...numbers, ...newArray]);
    }, 3000);
  };

  return (
    <CustomView margin>
      <Title text="Scroll infinito" safe />
      <FlatList
        data={numbers}
        onEndReached={loadMore}
        onEndReachedThreshold={0.7}
        keyExtractor={item => item.toString()}
        renderItem={({item}) => (
          <Text
            style={{
              height: 300,
              backgroundColor: colors.primary,
              color: 'white',
              fontSize: 50,
            }}>
            {item}
          </Text>
        )}
      />
    </CustomView>
  );
};
