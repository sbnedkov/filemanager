import React from 'react';

import FileManager from 'react-file-manager-ui';

import client from './client';
import getCsrfToken from './utils';

const FILE = 1;
// const FOLDER = 2;

export default function DirList () {
    return (<FileManager
        getList={getList}
        createDirectory={createDirectory}
        deletePaths={deletePaths}
        openFile={openFile}
        uploadFiles={uploadFiles}
        rename={rename}
        getDownloadLink={getDownloadLink}
        getUploadLink={getUploadLink}
        getFileSizeBytes={getFileSizeBytes}
        getFileChangedDate={getFileChangedDate}
        features={[
            'createDirectory',
            'uploadFiles',
            'deletePaths',
            'rename',
            'openFile',
            'getDownloadLink',
            'getFileSizeBytes',
            'getFileChangedDate'
        ]}
    />);
}

async function getList () {
    const response = await client({ method: 'GET', path: '/api/files' });
    return response.entity._embedded.files.map(file => ({
        name: file.name,
        type: file.type
    }));
}

function createDirectory () {
    console.log('createDirectory', arguments);
}

function deletePaths () {
    console.log('deletePaths', arguments);
}

async function uploadFiles (path, files) {
    for await (const file of files) {
        await client({
            method: 'POST',
            path: '/api/files',
            entity: {
                name: file.name,
                type: FILE
            },
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }
}

function getUploadLink (currentPath, filename) {
    return `/api/fileupload${currentPath}/${filename}?_csrf=${getCsrfToken()}`;
}

function rename () {
    console.log('rename', arguments);
}

function openFile () {
    console.log('openFile', arguments);
}

async function getDownloadLink ([path]) {
    const response = await fetch(`/api/filedownload${path}`);
    return response.text();
}

function getFileSizeBytes (/* path */) {
    return Math.random() * Math.pow(10, 7)
}

function getFileChangedDate (/* path */) {
    const time = Math.round(Math.random() * 1e12);
    return new Date(time);
}
