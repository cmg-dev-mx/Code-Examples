import {createDrawerNavigator} from '@react-navigation/drawer';
import {StackNavigator} from './StackNavigator';
import {ProfileScreen} from '../screens/profile/ProfileScreen';

export type DrawerRootStackParams = {
  StackNavigator: undefined;
  Profile: undefined;
};

const Drawer = createDrawerNavigator<DrawerRootStackParams>();

export const SideMenuNavigator = () => {
  return (
    <Drawer.Navigator>
      <Drawer.Screen name="StackNavigator" component={StackNavigator} />
      <Drawer.Screen name="Profile" component={ProfileScreen} />
    </Drawer.Navigator>
  );
};
