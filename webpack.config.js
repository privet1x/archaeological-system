module.exports = {
    devtool: 'source-map',
    output: {
        filename: 'react-app.js'
    },
    module: {
        rules: [{
            test: /\.js$|jsx/,
            exclude: /node_modules/,
            loader: "babel-loader",
            options: {
                presets: ['@babel/preset-env', '@babel/preset-react']
            }
        },{
            test: /\.css$/,
            use: ['style-loader', 'css-loader']
        }]
    },
    resolve: {
        extensions: ['.js', '.jsx']
    }
};