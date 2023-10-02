var path = require('path');

module.exports = {
    mode: 'development',
    entry: './src/main/js/app.js',
    devtool: 'source-map',
    cache: true,
    output: {
        path: __dirname,
        filename: 'bundle.js'
    },
    module: {
        rules: [{
            test: path.join(__dirname, '.'),
            exclude: /(node_modules)/,
            use: [{
                loader: 'babel-loader',
                options: {
                    presets: ['@babel/preset-env', '@babel/preset-react']
                }
            }]
        }, {
            test: /\.css$/i,
            use: [
                'style-loader',
                'css-loader'
            ],
        }]
    }
};
