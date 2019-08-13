(function () {
    'use strict';

    var HtmlWebpackPlugin = require('html-webpack-plugin');
    var CleanPlugin       = require('clean-webpack-plugin');
    var CopyPlugin        = require('copy-webpack-plugin');

    var contextPath   = '/pulzator-parent/';
    var targetFolder  = __dirname + '/../resources/static';

    var plugins = [
        new CleanPlugin(targetFolder, { allowExternal: true }),
        new HtmlWebpackPlugin({
            template: './src/index.html'
        }),
        new CopyPlugin([{
            from: '**/*.css',
            to: targetFolder,
            context: './src/'
        }, {
            from: '**/*.html',
            to: targetFolder,
            context: './src'
        }])
    ];

    module.exports = {
        entry: {
            app: 'main.js',
            lib: 'lib.js'
        },
        output: {
            path: targetFolder,
            filename: '[name].js',
            publicPath: contextPath
        },
        resolve: {
            extensions: [ '*', '.js' ],
            modules: [ 'node_modules', __dirname + '/src' ]
        },
        module: {
            rules: [{
                test: /\.js$/,
                enforce: 'pre',
                exclude: /node_modules/,
                use: [{
                    loader: 'jshint-loader',
                    options: {
                        camelcase: true,
                        curly: true,
                        eqeqeq: true,
                        quotmark: 'single',
                        emitErrors: true,
                        failOnHint: true
                    }
                }]
            }, {
                test: /\.js$/,
                loaders: [ 'ng-annotate-loader' ]
            }]
        },
        plugins: plugins,
        devServer: {
            port: 11111,
            contentBase: targetFolder,
            historyApiFallback: {
                index: contextPath + 'index.html'
            },
            proxy: {

            }
        }
    };

}());
