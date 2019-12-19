import {
	NativeModules,
	PickerIOS,
	Platform,
} from 'react-native';
import WheelPicker from './src/PickerCustomAndroid';
const { RnWheelPickerModule } = NativeModules;

export { WheelPicker, RnWheelPickerModule };
export default (Platform.OS == 'android' ? WheelPicker : PickerIOS);
