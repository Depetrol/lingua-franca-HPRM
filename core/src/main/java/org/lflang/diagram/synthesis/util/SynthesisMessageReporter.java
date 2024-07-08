/*************
 * Copyright (c) 2020, Kiel University.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ***************/
package org.lflang.diagram.synthesis.util;

import de.cau.cs.kieler.klighd.Klighd;
import java.nio.file.Path;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.lflang.MessageReporterBase;
import org.lflang.generator.Range;

/**
 * @author Alexander Schulz-Rosengarten
 */
public class SynthesisMessageReporter extends MessageReporterBase {

  @Override
  protected void reportWithoutPosition(DiagnosticSeverity severity, String message) {
    var status =
        switch (severity) {
          case Error -> IStatus.ERROR;
          case Warning -> IStatus.WARNING;
          case Information, Hint -> IStatus.INFO;
        };
    Klighd.log(new Status(status, SynthesisMessageReporter.class, message));
  }

  @Override
  protected void report(Path path, Range range, DiagnosticSeverity severity, String message) {
    reportWithoutPosition(severity, message);
  }

  @Override
  protected void reportOnNode(EObject node, DiagnosticSeverity severity, String message) {
    reportWithoutPosition(severity, message);
  }
}
