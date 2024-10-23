import {View, Text} from 'react-native';
import {globalStyles} from '../../../config/theme/theme';
import {ScrollView} from 'react-native-gesture-handler';
import {Title} from '../../components/ui/Title';

export const menuItems = [
  // 01 - Animation Menu Items
  {
    name: 'Animation 101',
    icon: 'cube-outline',
    component: 'Animation101Screen',
  },
  {
    name: 'Animation 102',
    icon: 'cube-outline',
    component: 'Animation102Screen',
  },
  // 02 - Menu Items
  {
    name: 'Pull to refresh',
    icon: 'refresh-outline',
    component: 'PullToRefreshScreen',
  },
  {
    name: 'Section List',
    icon: 'list-outline',
    component: 'CVustomSectionListScreen',
  },
  {
    name: 'Modal',
    icon: 'copy-outline',
    component: 'ModalScreen',
  },
  {
    name: 'Infinite Scroll',
    icon: 'download-outline',
    component: 'InfiniteScrollScreen',
  },
  {
    name: 'Slides',
    icon: 'flower-outline',
    component: 'SlidesScreen',
  },
  {
    name: 'Themes',
    icon: 'flask-outline',
    component: 'ChangeThemeScreen',
  },
  // 03 - UI Menu Items
  {
    name: 'Switches',
    icon: 'toggle-outline',
    component: 'SwitchScreen',
  },
  {
    name: 'Alerts',
    icon: 'alert-circle-outline',
    component: 'AlertScreen',
  },
  {
    name: 'Text Inputs',
    icon: 'document-text-outline',
    component: 'TextInputScreen',
  },
];

export const HomeScreen = () => {
  return (
    <View style={globalStyles.mainContainer}>
      <View style={globalStyles.globalMargin}>
        <ScrollView>
          <Title text="Opciones del menú" safe />

          {menuItems.map((item, index) => (
            <Text key={index}>{item.name}</Text>
          ))}
        </ScrollView>
      </View>
    </View>
  );
};
