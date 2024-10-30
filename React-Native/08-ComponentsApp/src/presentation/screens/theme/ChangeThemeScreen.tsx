import {View} from 'react-native';
import {CustomView} from '../../components/ui/CustomView';
import {Title} from '../../components/ui/Title';
import {Button} from '../../components/ui/Button';

export const ChangeThemeScreen = () => {
  return (
    <CustomView margin>
      <Title text="Cambiar tema" safe />

      <Button
        text="Light mode"
        onPress={() => console.log('Cambiar a tema claro')}
      />

      <View style={{height: 10}} />

      <Button
        text="Dark mode"
        onPress={() => console.log('Cambiar a tema oscuro')}
      />
    </CustomView>
  );
};
