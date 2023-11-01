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

async function uploadFiles () {
    console.log('uploadFiles', arguments);
}

function getUploadLink (filepath, filename) {
    return `/api/fileupload/${filename}`;
}

function rename () {
    console.log('rename', arguments);
}

function openFile () {
    console.log('openFile', arguments);
}

async function getDownloadLink ([path]) {
    return fetch(`/api/filedownload${path}`).then(response => {
        if (!response.ok) {
            throw new Error(response);
        }

        return response.text();
    });
}

function getFileSizeBytes (path) {
    return Math.random() * Math.pow(10, 7)
}

function getFileChangedDate (path) {
    const time = Math.round(Math.random() * 1e12);
    return new Date(time);
}
