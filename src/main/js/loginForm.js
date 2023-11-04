import React, { useState } from 'react';

function LoginForm() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = () => {
        console.log('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>');
        if (email && password) {
            alert(email);
        } else {
            alert('Please supply credentials');
        }
    };

    return (
        <div className="login-form">
            <input type="text" value={email} placeholder="Email" />
            <input type="password" value={password} placeholder="Password" />
            <button type="submit" onClick={handleLogin}>Login</button>
        </div>
    );
}

export default LoginForm;
