const HtmlWebpackPlugin = require('html-webpack-plugin')
const path = require('path');

module.exports = {
    entry: './src/index.js',
    devtool: 'inline-source-map',
    output: {
        path: path.resolve(__dirname),
        filename: 'bundle.js',
        libraryTarget: 'umd',
        publicPath: '/'
    },

    devServer: {
        contentBase: path.resolve(__dirname) + '/src',
        historyApiFallback: true,
        compress: true,
        port: 9000,
        host: 'localhost',
        open: true,
        proxy: {
            '/test': {
                target: 'http://127.0.0.1:8080',
                pathRewrite: {'^/test' : ''}
            }
        }
    },

    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules|bower_components|build)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['env', 'react', 'stage-1']
                    }
                }
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            filename: 'index.html',
            template: 'public/index.html'
        })
    ]
};
