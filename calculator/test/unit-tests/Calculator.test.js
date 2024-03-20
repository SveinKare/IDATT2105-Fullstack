import {mount} from "@vue/test-utils";
import Calculator from "@/components/Calculator.vue";
import {beforeEach, describe, expect, it, vi} from "vitest";

describe("Calculator test suite", () => {
    let wrapper;
    beforeEach(() => {
        wrapper = mount(Calculator);
    })

    const functionCall = (methodName, buttonId) => {
        return async () => {
            const spy = vi.spyOn(wrapper.vm, methodName);
            await wrapper.find(buttonId).trigger("click");
            expect(spy).toHaveBeenCalled();
        };
    };

    it("Buttons trigger updateField", functionCall("updateField", "#button1"));
    it("Equals button triggers calculate", functionCall("calculate", "#equals"));
    it("Backspace button triggers backspace", functionCall("backSpace", "#backspace"));
    it("C button triggers clearAll", functionCall("clearAll", "#clear"));
});