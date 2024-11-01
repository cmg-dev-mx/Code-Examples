import {useContext, useState} from 'react';
import {ActivityIndicator, View} from 'react-native';
import {FlatList} from 'react-native-gesture-handler';
import {FadeInImage} from '../../components/ui/FadeInImage';
import {colors} from '../../../config/theme/theme';
import {ThemeContext} from '../../context/ThemeContext';

export const InfiniteScrollScreen = () => {
  const {colors} = useContext(ThemeContext);
  const [numbers, setNumbers] = useState([0, 1, 2, 3, 4, 5, 6, 7, 8, 9]);

  const loadMore = () => {
    console.log('Cargando más...');
    const newArray = Array.from({length: 10}, (_, i) => i + numbers.length);

    setTimeout(() => {
      setNumbers([...numbers, ...newArray]);
    }, 3000);
  };

  return (
    <View style={{backgroundColor: 'black'}}>
      <FlatList
        data={numbers}
        onEndReached={loadMore}
        onEndReachedThreshold={0.7}
        keyExtractor={item => item.toString()}
        renderItem={({item}) => <ListItem number={item} />}
        ListFooterComponent={() => (
          <View style={{height: 150, justifyContent: 'center'}}>
            <ActivityIndicator size={40} color={colors.primary} />
          </View>
        )}
      />
    </View>
  );
};

interface ListItemProps {
  number: number;
}

const ListItem = ({number}: ListItemProps) => {
  return (
    <FadeInImage
      uri={`https://picsum.photos/id/${number}/500/400`}
      style={{width: '100%', height: 400}}
    />
  );
};
