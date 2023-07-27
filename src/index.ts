import { registerPlugin } from '@capacitor/core';

import type { RootCheckerPlugin } from './definitions';

const RootChecker = registerPlugin<RootCheckerPlugin>('RootChecker', {
  web: () => import('./web').then(m => new m.RootCheckerWeb()),
});

export * from './definitions';
export { RootChecker };
