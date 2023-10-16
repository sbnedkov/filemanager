import React from 'react';
import { createRoot } from 'react-dom/client';

import DirList from './dirlist';

const container = document.getElementById('react');
const root = createRoot(container);
root.render(<DirList/>);
