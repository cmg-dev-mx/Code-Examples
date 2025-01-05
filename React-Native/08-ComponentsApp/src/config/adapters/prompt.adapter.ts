import prompt from 'react-native-prompt-android';

interface Props {
  title: string;
  message: string;
  buttons: PromptButton[];
  promptType: 'default' | 'plain-text' | 'secure-text';
  defaultValue?: string;
  placeholder?: string;
}

interface PromptButton {
  text: string;
  onPress: () => void;
  style?: 'default' | 'cancel' | 'destructive';
}

export const showPrompt = ({
  title,
  message,
  buttons,
  promptType,
  defaultValue,
  placeholder,
}: Props) => {
  prompt(title, message, buttons, {
    type: promptType,
    cancelable: false,
    defaultValue: defaultValue,
    placeholder: placeholder,
  });
};
