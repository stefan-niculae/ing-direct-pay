/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */
'use strict';

var React = require('react-native');
var {
  AppRegistry,
  StyleSheet,
  Text,
  View,
} = React;
var BarcodeScanner = require('react-native-barcodescanner');

var HomeBankPay = React.createClass({
    getInitialState: function() {
	return {
	    torchMode: 'off',
	    cameraType: 'back'
	};
    },
  render: function() {
      return (
	      <BarcodeScanner onBarCodeRead={this._onBarCodeRead}
	  style={{ flex: 1 }}
	  torchMode={this.state.torchMode}
	  cameraType={this.state.cameraType} />
    );
  },
    _onBarCodeRead(e) {
	console.log(e);
    }
});

var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('HomeBankPay', () => HomeBankPay);
