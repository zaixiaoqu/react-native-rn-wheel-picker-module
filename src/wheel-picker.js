/**
 * @prettier
 * @flow
 * */

import React from "react"
import { requireNativeComponent,PixelRatio } from "react-native"

// eslint-disable-next-line no-use-before-define
const WheelPickerView = requireNativeComponent( "WheelPicker", WheelPicker );

type Props = {
    onItemSelected: any => void,
    data: Array<any>,
    isCurved?: boolean,
    isCyclic?: boolean,
    isAtmospheric?: boolean,
    selectedItemTextColor?: string,
    itemSpace?: number,
    visibleItemCount?: number,
    renderIndicator?: boolean,
    indicatorColor?: string,
    indicatorSize?: number,
    isCurtain?: boolean,
    curtainColor?: string,
    itemTextColor?: string,
    itemTextSize?: number,
    itemTextAlign?: 'left' | 'center' | 'right',
    selectedItemPosition?: number,
    backgroundColor?: string,
    allowFontScaling?: boolean
}

class WheelPicker extends React.Component<Props, State> {

    state = {
        lastUpdateTime: (new Date()).getTime()
    }

    static defaultProps = {
        style: {
           
        }
    }

    componentWillReceiveProps(nextProps) {
        this.setState({
            lastUpdateTime: (new Date()).getTime()
        });
    }

    onItemSelected = ( event: any ) => {
        if ( this.props.onItemSelected ) {
            this.props.onItemSelected( event.nativeEvent )
        }
    }
    
    render() {
        const properties = {
            ...this.props
        }
        return (
            <WheelPickerView
                { ...properties }
                onChange={this.onItemSelected}
                lastUpdateTime={this.state.lastUpdateTime}
            />
        )
    }
}

export default WheelPicker;