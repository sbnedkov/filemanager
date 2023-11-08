import { Cookies } from 'react-cookie';

const cookies = new Cookies();
export default function getCsrfToken () {
    return cookies.get('XSRF-TOKEN');
}
