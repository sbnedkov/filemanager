const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class Dir extends React.Component{
	render() {
		return (
			<tr>
				<td>{ this.props.dir.name }</td>
			</tr>
		)
	}
}

class DirList extends React.Component {
    render() {
        const dirs = this.props.dirs.map(dir =>
            <Dir key={ dir._links.self.href } dir={ dir }/>
        );

        return (
            <table>
                <tbody>
                    <tr>
                        <th>Dir name</th>
                    </tr>
                    { dirs }
                </tbody>
            </table>
        );
    }
}

class App extends React.Component {
    constructor (props) {
        super(props);
        this.state = { dirs: [] };
    }

    componentDidMount () {
        client({ method: 'GET', path: '/api/dirs' }).done(response => {
            this.setState({ dirs: response.entity._embedded.dirs });
        });
    }

    render () {
        return (
            <DirList dirs={ this.state.dirs }/>
        );
    }
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
