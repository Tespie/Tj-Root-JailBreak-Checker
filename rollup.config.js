export default {
  input: 'dist/index.js',
  output: [
    {
      file: 'dist/root-checker.js',
      format: 'iife',
      name: 'capacitorRootChecker',
      globals: {
        '@capacitor/core': 'capacitorExports',
      },
      sourcemap: true,
      inlineDynamicImports: true,
    },
    {
      file: 'dist/plugin.cjs.js',
      format: 'cjs',
      sourcemap: true,
      inlineDynamicImports: true,
    },
  ],
  external: ['@capacitor/core'],
};
