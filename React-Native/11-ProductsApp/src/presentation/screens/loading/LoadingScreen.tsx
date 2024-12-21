import {Button, Icon, Layout, Text} from '@ui-kitten/components';

export const LoadingScreen = () => {
  return (
    <Layout style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
      <Text>LoadingScreen</Text>
      <Button accessoryLeft={<Icon name="facebook" />}>Cerrar sesión</Button>
    </Layout>
  );
};
