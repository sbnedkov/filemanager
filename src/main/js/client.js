import rest from 'rest';
import defaultRequest from 'rest/interceptor/defaultRequest';
import mime from 'rest/interceptor/mime';
import uriTemplateInterceptor from './uriTemplateInterceptor';
import errorCode from 'rest/interceptor/errorCode';
import csrf from 'rest/interceptor/csrf';
import baseRegistry from 'rest/mime/registry';

import getCsrfToken from './utils';
import uriListConverter from './uriListConverter';

const registry = baseRegistry.child();
import hal from 'rest/mime/type/application/hal';

registry.register('text/uri-list', uriListConverter);
registry.register('application/hal+json', hal);

export default rest
    .wrap(mime, { registry: registry })
    .wrap(uriTemplateInterceptor)
    .wrap(csrf, { token: getCsrfToken() })
    .wrap(errorCode)
    .wrap(defaultRequest, { params: { _csrf: getCsrfToken() }, headers: { Accept: 'application/hal+json' }});
