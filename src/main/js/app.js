import React from 'react';
import { createRoot } from 'react-dom/client';

import DirList from './dirlist';

class App extends React.Component {
    constructor (props) {
        super(props);
        // this.state = { dirs: [] };
    }

    componentDidMount () {
    }

    render () {
        return (
            <DirList/>
        );
    }
}

const container = document.getElementById('react');
const root = createRoot(container);
root.render(<App/>);
