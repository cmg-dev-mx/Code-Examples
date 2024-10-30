import {RefreshControl, Text, View} from 'react-native';
import {CustomView} from '../../components/ui/CustomView';
import {Title} from '../../components/ui/Title';
import {ScrollView} from 'react-native-gesture-handler';
import {useSafeAreaInsets} from 'react-native-safe-area-context';
import {useState} from 'react';
import {colors, globalStyles} from '../../../config/theme/theme';

export const PullToRefreshScreen = () => {
  const [isRefreshing, setIsRefreshing] = useState(false);

  const {top} = useSafeAreaInsets();

  const onRefresh = () => {
    setIsRefreshing(true);
    setTimeout(() => {
      setIsRefreshing(false);
    }, 4000);
  };

  return (
    <ScrollView
      refreshControl={
        <RefreshControl
          refreshing={isRefreshing}
          progressViewOffset={top}
          onRefresh={onRefresh}
          colors={[colors.primary, 'red', 'orange', 'green']}
        />
      }
      style={[globalStyles.mainContainer, globalStyles.globalMargin]}>
      <Title text="Pull to refresh" safe />
    </ScrollView>
  );
};
