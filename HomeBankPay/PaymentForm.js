'use strict';

var React = require('react-native');
var Dropdown = require('react-native-dropdown-android');

var {
  StyleSheet,
  Text,
  View,
  TextInput,
  TouchableHighlight,
} = React;

var PaymentForm = React.createClass({
  propTypes: {
    receiverName: React.PropTypes.string.isRequired,
    iban: React.PropTypes.string.isRequired,
    amount: React.PropTypes.number.isRequired,
    details: React.PropTypes.string.isRequired,
  },

  getInitialState: function() {
    return {
      receiverName: this.props.receiverName,
      details: this.props.details,
      iban: this.props.iban,
      amount: this.props.amount,
    };
  },
  render: function() {
    return (
      <View style={styles.container}>
        <View style={{ height: 50 }}></View>

        <View style={styles.formLine}>
          <Text style={styles.formLabel}>Name</Text>
          <TextInput style={styles.formInput}
            onChange={(text)=>this.setState({receiverName: text})}
            value={this.state.receiverName}
            underlineColorAndroid={ING_ORANGE}
          />
        </View>

        <View style={styles.formLine}>
          <Text style={styles.formLabel}>Details</Text>
          <TextInput style={styles.formInput}
            onChange={(text)=>this.setState({details: text})}
            value={this.state.details}
            underlineColorAndroid={ING_ORANGE}
          />
        </View>

        <View style={styles.formLine}>
          <Text style={styles.formLabel}>IBAN</Text>
          <TextInput style={styles.formInput}
            onChange={(text)=>this.setState({iban: text})}
            value={this.state.iban}
            underlineColorAndroid={ING_ORANGE}
          />
        </View>

        <View style={styles.formLine}>
          <Text style={styles.formLabel}>Amount</Text>
          <TextInput style={styles.formInput}
            onChange={(text)=>this.setState({amount: text})}
            value={this.state.amount.toString()}
            underlineColorAndroid={ING_ORANGE}
          />
        </View>

        <View style={{ height: 100 }}></View>
        <View style={styles.buttonLine}>
          <TouchableHighlight onPress={this.props.onRejectPayment} style={styles.highlight}>
            <Text style={styles.buttonCancel}>
              Reject
            </Text>
          </TouchableHighlight>
          <TouchableHighlight onPress={this.props.onAcceptPayment} style={styles.highlight}>
            <Text style={styles.buttonOk}>
              Accept
            </Text>
          </TouchableHighlight>
        </View>
      </View>
    );
  }
});


var ING_ORANGE = '#e66500';
var ING_BLUE = '063e93';

var styles = StyleSheet.create({
  container: {
    flex: 1,
    // justifyContent: 'center',
    // alignItems: 'center',
    backgroundColor: 'white',
    alignItems: 'stretch'
  },
  spacer: {
    height: 50,
  },

  formLine: {
    flexDirection: 'row',
    flexWrap: 'nowrap',
    alignItems: 'center',
    marginLeft: 25,
    marginRight: 35,
    justifyContent: 'center',
    height: 35,
    marginTop: 7,
    marginBottom: 15,
  },
  formLabel: {
    color: 'black',
    fontSize: 14,
    textAlign: 'left',
    flex: .2,
  },
  formInput: {
    fontSize: 12,
    flex: .8,
  },

  buttonLine: {
    flexDirection: 'row',
    flexWrap: 'nowrap',
    alignItems: 'center',
    marginLeft: 25,
    marginRight: 35,
    justifyContent: 'center',
    height: 60,
    marginTop: 7,
    marginBottom: 15,
  },

  buttonCancel: {
    backgroundColor: 'rgb(99, 99, 99)',
    color: 'white',
    padding: 1.5,
    width: 125,
    textAlign: 'center',
  },

  buttonOk: {
    backgroundColor: ING_ORANGE,
    color: 'white',
    padding: 1.5,
    width: 125,
    textAlign: 'center',
  },
  highlight: {
    margin: 15,
  },
});

module.exports = PaymentForm;
