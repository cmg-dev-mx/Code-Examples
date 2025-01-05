import {Button} from '@ui-kitten/components';
import {StyleProp, ViewStyle} from 'react-native';
import {MyIcon} from './MyIcon';

interface Props {
  iconName: string;
  onPress: () => void;
  style?: StyleProp<ViewStyle>;
}

export const FAB = ({iconName, onPress, style}: Props) => {
  return (
    <Button
      style={[
        style,
        {
          shadowColor: 'black',
          shadowOffset: {width: 0, height: 10},
          shadowOpacity: 0.4,
          shadowRadius: 10,
          elevation: 3,
          borderRadius: 12,
        },
      ]}
      accessoryLeft={<MyIcon name={iconName} white />}
      onPress={onPress}
    />
  );
};
