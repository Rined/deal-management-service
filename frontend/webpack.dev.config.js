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
            // '/deal':'http://arch.project',
            // '/proposal':'http://arch.project',
            // '/auth':'http://arch.project',
            // '/templates':'http://arch.project',
            '/deal': {
                target: 'http://arch.homework',
                secure: false,
                changeOrigin: true
                // pathRewrite: {'^/deal' : ''}
            },
            '/proposal': {
                target: 'http://arch.homework',
                secure: false,
                changeOrigin: true,
                // pathRewrite: {'^/proposal' : ''}
            },
            '/template': {
                target: 'http://arch.homework',
                secure: false,
                changeOrigin: true
            },
            '/auth': {
                target: 'http://arch.homework',
                secure: false,
                changeOrigin: true
            },
            '/users': {
                target: 'http://arch.homework',
                secure: false,
                changeOrigin: true
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
