import {describe, it, expect, beforeEach} from "vitest";
import {useFormStore} from "@/stores/formStore.js";
import { setActivePinia, createPinia } from 'pinia'

describe("Form store test suite", () => {
    let store;
    beforeEach(() => {
        setActivePinia(createPinia());
        store = useFormStore();
    })

    it("UpdateName correctly sets name variable", () => {
        store.updateName("Svein");
        expect(store.name).toBe("Svein");
    });
    it("UpdateEmail correctly sets email variable", () => {
        store.updateEmail("example@gmail.com");
        expect(store.email).toBe("example@gmail.com");
    });
})
