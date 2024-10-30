import {Text, View} from 'react-native';
import {colors, globalStyles} from '../../../config/theme/theme';
import {useSafeAreaInsets} from 'react-native-safe-area-context';

interface Props {
  text: string;
  safe?: boolean;
  backgroundColor?: string;
}

export const Subtitle = ({
  text,
  safe = false,
  backgroundColor = colors.background,
}: Props) => {
  const {top} = useSafeAreaInsets();
  return (
    <Text
      style={{
        ...globalStyles.subtitle,
        marginTop: safe ? top : 0,
        marginBottom: 10,
        backgroundColor,
      }}>
      {text}
    </Text>
  );
};
