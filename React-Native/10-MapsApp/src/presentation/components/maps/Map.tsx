import React from 'react';
import {Platform} from 'react-native';
import MapView, {Marker, PROVIDER_GOOGLE} from 'react-native-maps';
import {Location} from '../../../infrastructure/interfaces/location';

interface Props {
  showUserLocation?: boolean;
  initialLocation: Location;
}

export const Map = ({showUserLocation = true, initialLocation}: Props) => {
  return (
    <>
      <MapView
        showsUserLocation={showUserLocation}
        provider={Platform.OS === 'ios' ? undefined : PROVIDER_GOOGLE}
        style={{flex: 1}}
        region={{
          latitude: initialLocation.latitude,
          longitude: initialLocation.longitude,
          latitudeDelta: 0.015,
          longitudeDelta: 0.0121,
        }}>
        {/* <Marker
          coordinate={{
            latitude: 19.432442,
            longitude: -99.133235,
          }}
          title="CDMX"
          description="Ciudad de México"
          image={require('../../../assets/marker.png')}
        /> */}
      </MapView>
    </>
  );
};
