'use strict';

var React = require('react-native');
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
    details: React.PropTypes.string.isRequired
  },
  getInitialState: function() {
    return {
      receiverName: this.props.receiverName,
      iban: this.props.iban,
      amount: this.props.amount,
      details: this.props.details
    };
  },
  render: function() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Payment Form</Text>
        <View style={styles.formLine}>
          <Text style={styles.formLabel}>Nume</Text>
          <TextInput style={styles.formInput}
            onChange={(text)=>this.setState({receiverName: text})}
            value={this.state.receiverName}/>
        </View>
      </View>
    );
  }
});

var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'white',
  },
  formLine: {
    flex: 1,
    flexDirection: 'row',
    flexWrap: 'nowrap',
    alignItems: 'flex-start'
  },
  formInput: {fontSize: 20, height: 40, width: 180},
  formLabel: {fontSize: 20, borderColor: 'gray', borderWidth: 1, textAlign: 'center', flex: 1}
});

module.exports = PaymentForm;
