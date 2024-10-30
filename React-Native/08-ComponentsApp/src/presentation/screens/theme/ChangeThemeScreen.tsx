import {View} from 'react-native';
import {CustomView} from '../../components/ui/CustomView';
import {Title} from '../../components/ui/Title';
import {Button} from '../../components/ui/Button';
import {useContext} from 'react';
import {ThemeContext} from '../../context/ThemeContext';

export const ChangeThemeScreen = () => {
  const {setTheme} = useContext(ThemeContext);

  return (
    <CustomView margin>
      <Title text="Cambiar tema" safe />

      <Button text="Light mode" onPress={() => setTheme('light')} />

      <View style={{height: 10}} />

      <Button text="Dark mode" onPress={() => setTheme('dark')} />
    </CustomView>
  );
};
