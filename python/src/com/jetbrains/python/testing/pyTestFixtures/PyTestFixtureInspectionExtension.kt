// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.jetbrains.python.testing.pyTestFixtures

import com.intellij.psi.PsiElement
import com.jetbrains.python.inspections.PyInspectionExtension
import com.jetbrains.python.psi.PyFunction
import com.jetbrains.python.psi.PyNamedParameter
import com.jetbrains.python.psi.types.TypeEvalContext

/**
 * fixture-based parameters should be skipped by inspection
 */
object PyTestFixtureInspectionExtension : PyInspectionExtension() {
  override fun ignoreUnused(local: PsiElement) = local is PyNamedParameter
                                                 && hasFixture(local, TypeEvalContext.codeAnalysis(local.project,
                                                                                                   local.containingFile))

  override fun ignoreShadowed(element: PsiElement) = element is PyFunction && element.isFixture()
}