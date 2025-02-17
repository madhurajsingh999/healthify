// webpack.config.js
const path = require('path');
module.exports = {
    resolve: {
      alias: {
        '@': path.resolve(__dirname, 'src')
      }
    },
    module: {
      rules: [
        {
          test: /\.css$/,
          use: [
            'style-loader',
            'resolve-url-loader',
            'css-loader',
          ],
        },
      ],
    }
  };