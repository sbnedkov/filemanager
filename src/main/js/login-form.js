import React from 'react';
import { useCookies } from 'react-cookie';

export default function LoginForm () {
    const [cookies] = useCookies(['XSRF-TOKEN']);
    const loginAction = `/login?_csrf=${cookies['XSRF-TOKEN']}`;

    return (
        <div>
            <form action={loginAction} method="post">
                <div><label> Email: <input type="text" name="username"/> </label></div>
                <div><label> Password: <input type="password" name="password"/> </label></div>
                <div><input type="submit" value="Sign In"/></div>
            </form>
        </div>
    );
}
