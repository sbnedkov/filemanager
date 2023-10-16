import React from 'react';

import FileManager from 'react-file-manager-ui';

import client from './client';

const FILE = 1;
const FOLDER = 2;

export default function DirList () {
    return (<FileManager
        getList={getList}
        createDirectory={createDirectory}
        deletePaths={deletePaths}
        openFile={openFile}
        uploadFiles={uploadFiles}
        rename={rename}
        downloadFile={downloadFile}
        getFileSizeBytes={getFileSizeBytes}
        getFileChangedDate={getFileChangedDate}
        features={[
            'createDirectory',
            'uploadFiles',
            'deletePaths',
            'rename',
            'openFile',
            'downloadFile',
            'getFileSizeBytes',
            'getFileChangedDate'
        ]}
    />);
}

async function getList () {
    return new Promise((resolve, _reject) => client({ method: 'GET', path: '/api/dirs' }).done(response =>
        resolve(response.entity._embedded.dirs.map(dir => ({
            name: dir.name,
            type: Math.random() > 0.5 ? FOLDER : FILE
        })))));
}

function createDirectory () {
    console.log('createDirectory', arguments);
}

function deletePaths () {
    console.log('deletePaths', arguments);
}

function uploadFiles () {
    console.log('uploadFiles', arguments);
}

function rename () {
    console.log('rename', arguments);
}

function openFile () {
    console.log('openFile', arguments);
    alert('OPEN');
}

function downloadFile (path) {
    return `/api/filedownload${path}`;
}

function getFileSizeBytes () {
    return Math.random() * Math.pow(10, 7)
}

function getFileChangedDate () {
    const time = Math.round(Math.random() * 1e12);
    return new Date(time);
}
