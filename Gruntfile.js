module.exports = function(grunt) {

  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),

    /****
    * Concateno las librerias de cada pagina en un unico archivo javascript
    ****/
    concat: {
      options: {
        stripBanners: true,
        banner: '/*! <%= pkg.name %> - v<%= pkg.version %> - ' +
          '<%= grunt.template.today("yyyy-mm-dd") %> */',
        separator: ';'
      },
      /****
      * Librerias y codigo del Home
      ****/
      application: {
        src: [
          'src/main/webapp/assets/app.js',         
          'src/main/webapp/assets/controllers/app.js',
        ],
        dest: 'build/libs/libs_home.js'
      }
    },

    /****
    * Minifica, comprime, elimina comentarios, etc
    ****/
    uglify: {
      my_target: {
        files: [{
            expand: true,
            cwd: '../curvometal-web',
            src: ['build/*.js', 'build/libs/*.js'],
            dest: 'build/min/'
        }]
      }
    },

    /****
    * Chequeo de calidad del codigo
    ****/
    gjslint: {
      options: {
        reporter: {
          name: 'console'
        },
        force: false
      },
      lib: {
        src: ['js/*.js', '!js/custom.js'],
      }
    },

    /****
    * Autocorreccion de errores de codigo
    ****/
    fixjsstyle: {
      options: {
        reporter: {
          name: 'console'
        },
        force: false
      },
      lib: {
        src: ['js/*.js'],
      }
    },  
    
  });

  grunt.loadNpmTasks('grunt-gjslint');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-html-convert');
  grunt.loadNpmTasks('grunt-contrib-imagemin');
  grunt.loadNpmTasks("grunt-phplint");
  grunt.loadNpmTasks("grunt-phpcs");

  grunt.registerTask('template', ['htmlConvert']);
  grunt.registerTask('quality', ['gjslint']);
  grunt.registerTask('fixquality', ['fixjsstyle']);
  grunt.registerTask('compile', ['quality','htmlConvert', 'concat', 'uglify']);
  grunt.registerTask('deploy', ['compile', 'copy:noImages']);

};