import {Alert, Text, View} from 'react-native';
import {CustomView} from '../../components/ui/CustomView';
import {Title} from '../../components/ui/Title';
import {globalStyles} from '../../../config/theme/theme';
import {Button} from '../../components/ui/Button';

export const AlertScreen = () => {
  const createTwoButtonAlert = () =>
    Alert.alert('Alert Title', 'Alert Message', [
      {
        text: 'Cancel',
        onPress: () => console.log('Cancel Pressed'),
        style: 'destructive',
      },
      {
        text: 'OK',
        onPress: () => console.log('OK Pressed'),
      },
    ]);

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
      },
    );

  return (
    <CustomView style={globalStyles.globalMargin}>
      <Title safe text="Alertas" />

      <Button text="Alerta - 2 Botones" onPress={createTwoButtonAlert} />
      <View style={{height: 10}} />
      <Button text="Alerta - 3 Botones" onPress={createThreeButtonAlert} />
      <View style={{height: 10}} />
      <Button text="Prompt - Input" onPress={() => {}} />
    </CustomView>
  );
};
