import {
  KeyboardAvoidingView,
  Platform,
  Text,
  TextInput,
  View,
} from 'react-native';
import {CustomView} from '../../components/ui/CustomView';
import {Title} from '../../components/ui/Title';
import {globalStyles} from '../../../config/theme/theme';
import {Card} from '../../components/ui/Card';
import {useContext, useState} from 'react';
import {ScrollView} from 'react-native-gesture-handler';
import {ThemeContext} from '../../context/ThemeContext';

export const TextInputScreen = () => {
  const {colors} = useContext(ThemeContext);

  const [form, setForm] = useState({
    name: '',
    email: '',
    phone: '',
  });

  return (
    <KeyboardAvoidingView
      behavior={Platform.OS === 'ios' ? 'padding' : undefined}>
      <ScrollView>
        <CustomView margin>
          <Title text="TextInput" safe />

          <Card>
            <TextInput
              style={[
                globalStyles.input,
                {
                  color: colors.text,
                  borderColor: colors.text,
                },
              ]}
              placeholderTextColor={colors.textHint}
              placeholder="Nombre completo"
              autoCapitalize={'words'}
              autoCorrect={false}
              onChangeText={value => setForm({...form, name: value})}
            />
            <TextInput
              style={[
                globalStyles.input,
                {
                  color: colors.text,
                  borderColor: colors.text,
                },
              ]}
              placeholderTextColor={colors.textHint}
              placeholder="Correo electrónico"
              autoCapitalize={'none'}
              autoCorrect={false}
              keyboardType="email-address"
              onChangeText={value => setForm({...form, email: value})}
            />
            <TextInput
              style={[
                globalStyles.input,
                {
                  color: colors.text,
                  borderColor: colors.text,
                },
              ]}
              placeholderTextColor={colors.textHint}
              placeholder="Teléfono"
              autoCapitalize={'none'}
              autoCorrect={false}
              keyboardType="phone-pad"
              onChangeText={value => setForm({...form, phone: value})}
            />
          </Card>

          <View style={{height: 10}} />

          <Card>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
            <Text style={{color: colors.text}}>
              {JSON.stringify(form, null, 2)}
            </Text>
          </Card>

          <View style={{height: 10}} />

          <Card>
            <TextInput
              style={globalStyles.input}
              placeholder="Teléfono"
              autoCapitalize={'none'}
              autoCorrect={false}
              keyboardType="phone-pad"
              onChangeText={value => setForm({...form, phone: value})}
            />
          </Card>

          <View style={{height: 20}} />
        </CustomView>
      </ScrollView>
    </KeyboardAvoidingView>
  );
};
