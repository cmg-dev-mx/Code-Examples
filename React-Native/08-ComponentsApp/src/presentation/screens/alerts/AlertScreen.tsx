import {Alert, View} from 'react-native';
import {CustomView} from '../../components/ui/CustomView';
import {Title} from '../../components/ui/Title';
import {globalStyles} from '../../../config/theme/theme';
import {Button} from '../../components/ui/Button';
import prompt from 'react-native-prompt-android';
import {showPrompt} from '../../../config/adapters/prompt.adapter';
import {useContext} from 'react';
import {ThemeContext} from '../../context/ThemeContext';

export const AlertScreen = () => {
  const {isDark} = useContext(ThemeContext);

  const createTwoButtonAlert = () =>
    Alert.alert(
      'Alert Title',
      'Alert Message',
      [
        {
          text: 'Cancel',
          onPress: () => console.log('Cancel Pressed'),
          style: 'destructive',
        },
        {
          text: 'OK',
          onPress: () => console.log('OK Pressed'),
        },
      ],
      {
        userInterfaceStyle: isDark ? 'dark' : 'light',
      },
    );

  const createThreeButtonAlert = () =>
    Alert.alert(
      'Alert Title',
      'Alert Message',
      [
        {
          text: 'OK',
          onPress: () => console.log('OK Pressed'),
        },
        {
          text: 'Ask me later',
          onPress: () => console.log('Ask me later Pressed'),
          style: 'cancel',
        },
        {
          text: 'Cancel',
          onPress: () => console.log('Cancel Pressed'),
          style: 'destructive',
        },
      ],
      {
        cancelable: true,
        onDismiss: () => console.log('onDismiss'),
        userInterfaceStyle: isDark ? 'dark' : 'light',
      },
    );

  const onShowPrompt = () => {
    // ! Código nativo
    // Alert.prompt(
    //   'Correo electronico',
    //   'Ingrese su correo electronico',
    //   (text: string) => console.log({text}),
    //   'secure-text',
    //   'Soy el valor por defecto',
    //   'email-address',
    // );

    showPrompt({
      title: 'Correo electronico',
      message: 'Ingrese su correo electronico',
      buttons: [
        {
          text: 'Cancelar',
          onPress: () => console.log('Cancelar'),
        },
        {
          text: 'Aceptar',
          onPress: () => console.log('Aceptar'),
        },
      ],
      promptType: 'secure-text',
      defaultValue: 'Soy el valor por defecto',
      placeholder: 'email-address',
    });
  };

  return (
    <CustomView style={globalStyles.globalMargin}>
      <Title safe text="Alertas" />

      <Button text="Alerta - 2 Botones" onPress={createTwoButtonAlert} />
      <View style={{height: 10}} />
      <Button text="Alerta - 3 Botones" onPress={createThreeButtonAlert} />
      <View style={{height: 10}} />
      <Button text="Prompt - Input" onPress={onShowPrompt} />
    </CustomView>
  );
};
