import {globalColors} from '../../theme/theme';
import Icon from 'react-native-vector-icons/MaterialIcons';

interface Props {
  name: string;
  size?: number;
  color?: string;
}

export const CustomIcon = ({name, size = 25, color = 'black'}: Props) => {
  return <Icon name={name} size={size} color={color} />;
};
