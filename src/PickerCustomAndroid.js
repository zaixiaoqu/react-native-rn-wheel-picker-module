'use strict';

import React, { Component } from 'react';
import {
    View,
    requireNativeComponent
} from 'react-native';
import PropTypes from 'prop-types';
import isEmptyObject from './isEmptyObject';
import WheelPickerView from './wheel-picker';
import { Size } from '../../../src/config/layout';

class PickerCustomAndroid extends Component {

    static propTypes = {
		onValueChange: PropTypes.func
    }
    
    _indexKey = 'index_';

    state = {
        lastUpdateTime: (new Date()).getTime()
    }

    /**
     * 得到子PickerCustomAndroid.Item的信息
     */
    _getChildrenInfo() {
        const props = this.props;
        let items = [];
        let selectedIndex = 0;
        let itemsIndexData = {};
		if (typeof props == 'object' &&
			!isEmptyObject(props) &&
			typeof props.children == 'object' &&
			!isEmptyObject(props.children)) {
            const currThis = this;
			React.Children.forEach(props.children, function (child, index) {
				if (child.props.value == props.selectedValue) {
					selectedIndex = index;
				}
                items.push(child.props.label);

                let labelValue = 0;
				try {
					labelValue = parseInt(child.props.value);
				} catch (e) {
				}
                itemsIndexData[currThis._indexKey+index] = {
                    value: labelValue,
                    label: child.props.label
                };
			});
        }
        let itemSpace = 0;
		if (typeof props == 'object' &&
		    !isEmptyObject(props) &&
			typeof props.itemStyle == 'object' &&
			!isEmptyObject(props.itemStyle) &&
		    typeof props.itemStyle.itemSpace != 'undefined') {
				itemSpace = props.itemStyle.itemSpace;
        }
        let textSize = 0;
		if (typeof props == 'object' &&
		    !isEmptyObject(props) &&
			typeof props.itemStyle == 'object' &&
			!isEmptyObject(props.itemStyle) &&
		    typeof props.itemStyle.fontSize != 'undefined') {
				textSize = props.itemStyle.fontSize;
		}
		let textColor = '';
		if (typeof props == 'object' &&
		    !isEmptyObject(props) &&
			typeof props.itemStyle == 'object' &&
			!isEmptyObject(props.itemStyle) &&
		    typeof props.itemStyle.color != 'undefined') {
				textColor = props.itemStyle.color;
		}
        if (typeof items != 'object' || isEmptyObject(items)) {
        }


        console .log('选择的indexindex', {
            items: items,
            selectedIndex: selectedIndex,
            itemsIndexData: itemsIndexData
        })
        return {
            items: items,
            selectedIndex: selectedIndex,
            itemsIndexData: itemsIndexData,
            textSize: textSize,
            textColor: textColor,
            itemSpace: itemSpace
        };
    }

    componentWillReceiveProps(nextProps) {
        this.setState({
            lastUpdateTime: (new Date()).getTime()
        });
    }

	render() {
        const allState = this._getChildrenInfo();
        const itemsIndexData = allState.itemsIndexData;
        return <WheelPickerView
                isCurved={true}
                isCyclic={false}
                {...this.props}
                itemSpace={allState.itemSpace}
                itemTextSize={allState.textSize}
                itemTextColor={'#CECECE'}
                isSameWidth={true}
                // visibleItemCount={5}
                selectedItemTextColor={allState.textColor}
                renderIndicator={false}
                indicatorSize={7}
                onItemSelected={(event) => {
                    console .log('选择的event',event)
                    if (typeof event == 'object' && (
                        typeof event.position == 'number' ||
                        typeof event.position == 'string'
                    ) && typeof this.props.onValueChange == 'function') {
                    } else {
                        return null;
                    }
                    if (typeof itemsIndexData != 'object' || 
                        isEmptyObject(itemsIndexData) ||
                        typeof itemsIndexData[this._indexKey+event.position] == 'undefined') {
                            return null;
                    }
                    this.props.onValueChange(itemsIndexData[this._indexKey+event.position]['value']);
                }}
                data={allState.items}
                selectedItemPosition={parseInt(allState.selectedIndex)}
                lastUpdateTime={this.state.lastUpdateTime}
        />
	}
}

class Item  extends Component {
	static propTypes = {
		value: PropTypes.oneOfType([
            PropTypes.string,
            PropTypes.number
        ]),
		label: PropTypes.oneOfType([
            PropTypes.string,
            PropTypes.number
        ]),
    }
    
	render () {
		return null;
	}
}

PickerCustomAndroid.Item = Item;
export default PickerCustomAndroid;
