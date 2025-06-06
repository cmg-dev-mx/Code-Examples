import {Modal, Platform, Text, View} from 'react-native';
import {CustomView} from '../../components/ui/CustomView';
import {Title} from '../../components/ui/Title';
import React, {useState} from 'react';
import {Button} from '../../components/ui/Button';
import {useContext} from 'react';
import {ThemeContext} from '../../context/ThemeContext';

export const ModalScreen = () => {
  const {colors} = useContext(ThemeContext);

  const [isVisible, setIsVisible] = useState(false);
  return (
    <CustomView margin>
      <Title text="Modal" safe />

      <Button
        text="Abrir modal"
        onPress={() => {
          setIsVisible(true);
        }}
      />

      <Modal visible={isVisible} animationType="slide">
        <View
          style={{
            flex: 1,
            backgroundColor: colors.backgroundTransparent,
          }}>
          <View
            style={{
              paddingHorizontal: 10,
            }}>
            <Title text="Modal content" safe />
          </View>

          <View style={{flex: 1}} />

          <Button
            text="Cerrar modal"
            onPress={() => {
              setIsVisible(false);
            }}
            style={{
              height: Platform.OS === 'android' ? 40 : 60,
              borderRadius: 0,
            }}
          />
        </View>
      </Modal>
    </CustomView>
  );
};
