import React from 'react';
import { createRoot } from 'react-dom/client';
import { CookiesProvider } from 'react-cookie';

import LoginForm from './login-form';

const container = document.getElementById('login');
const root = createRoot(container);
root.render(
    <CookiesProvider defaultSetOptions={{ path: '/' }}>
        <LoginForm/>
    </CookiesProvider>);
