import React from 'react';
import { createRoot } from 'react-dom/client';

import client from './client';
import DirList from './dirlist';

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

const container = document.getElementById('react');
const root = createRoot(container);
root.render(<App/>);
