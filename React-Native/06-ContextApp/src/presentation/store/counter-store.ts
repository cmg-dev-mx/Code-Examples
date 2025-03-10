import {create} from 'zustand';

export interface CounterState {
  count: number;
  increment: () => void;
  reset: () => void;
}

export const useCounterStore = create<CounterState>((set, get) => ({
  count: 0,

  increment: () => {
    set(state => ({count: state.count + 1}));
  },

  reset: () => {
    set({count: 0});
  },
}));
