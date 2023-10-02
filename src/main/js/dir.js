import React from 'react';

export default class Dir extends React.Component{
	render() {
		return (
			<tr>
				<td>{ this.props.dir.name }</td>
			</tr>
		)
	}
}
