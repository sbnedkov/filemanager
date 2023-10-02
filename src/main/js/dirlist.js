import React from 'react';

import FileManager from 'react-file-manager-ui';

import client from './client';

const FILE = 1;
const FOLDER = 2;

export default class DirList extends React.Component {
    render() {
        return (
            <FileManager
                getList={this.getList}
                createDirectory={this.createDirectory}
                deletePaths={this.deletePaths}
                openFile={this.openFile}
                uploadFiles={this.uploadFiles}
                rename={this.rename}
                features={['createDirectory', 'uploadFiles', 'deletePaths', 'rename', 'openFile']}
            />
        );
    }

    async getList () {
        return new Promise((resolve, _reject) => client({ method: 'GET', path: '/api/dirs' }).done(response =>
            resolve(response.entity._embedded.dirs.map(dir => ({
                name: dir.name,
                type: Math.random() > 0.5 ? FOLDER : FILE
            })))));
    }

    createDirectory () {
    }

    deletePaths () {
    }

    uploadFiles () {
    }

    rename () {
    }

    openFile () {
    }
}
