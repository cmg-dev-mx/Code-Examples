import {Text, View} from 'react-native';
import {CustomView} from '../../components/ui/CustomView';
import {Title} from '../../components/ui/Title';
import {Button} from '../../components/ui/Button';
import {useContext} from 'react';
import {ThemeContext} from '../../context/ThemeContext';
import {colors} from '../../../config/theme/theme';

export const ChangeThemeScreen = () => {
  const {setTheme, theme, colors} = useContext(ThemeContext);

  return (
    <CustomView margin>
      <Title text={`Cambiar tema: ${theme}`} safe />

      <Button text="Light mode" onPress={() => setTheme('light')} />

      <View style={{height: 10}} />

      <Button text="Dark mode" onPress={() => setTheme('dark')} />

      <Text style={{color: colors.text}}>
        {JSON.stringify(colors, null, 2)}
      </Text>
    </CustomView>
  );
};
