import { WebPlugin } from '@capacitor/core';

import type { RootCheckerPlugin } from './definitions';

export class RootCheckerWeb extends WebPlugin implements RootCheckerPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
