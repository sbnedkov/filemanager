import React from 'react';

import Dir from './dir';

export default class DirList extends React.Component {
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
